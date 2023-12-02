import axios, { AxiosRequestConfig } from 'axios';
import qs from 'qs';
import jwtDecode from 'jwt-decode';
import history from './history';

type Role = 'ROLE_OPERATOR' | 'ROLE_ADMIN';

export type TokenData = {
  exp: number;
  user_name: string;
  authorities: Role[];
};

type LoginResponse = {
  access_token: string;
  token_type: string;
  refresh_token: string;
  expires_in: number;
  scope: string;
  userFirstName: string;
  userId: number;
};

const token_key = 'authData';

export const BASE_URL =
  process.env.REACT_APP_BACKEND_URL ?? 'http://localhost:8080';

export const CLIENT_ID = process.env.REACT_APP_CLIENT_ID ?? 'dscatalog';

export const CLIENT_SECRET =
  process.env.REACT_APP_CLIENT_SECRET ?? 'dscatalog123';

const basicHeader = () => {
  return `Basic ${btoa(`${CLIENT_ID}:${CLIENT_SECRET}`)}`;
};

type LoginData = {
  username: string;
  password: string;
};

export const requestBackendLogin = (loginData: LoginData) => {
  const headers = {
    'Content-Type': 'application/x-www-form-urlencoded',
    Authorization: basicHeader(),
  };
  const data = qs.stringify({
    ...loginData,
    grant_type: 'password',
  });

  return axios({
    method: 'POST',
    baseURL: BASE_URL,
    url: '/oauth/token',
    data,
    headers,
  });
};

export const requestBackend = (config: AxiosRequestConfig) => {
  const headers = config.withCredentials
    ? {
        ...config.headers,
        Authorization: `Bearer ${getAuthData().access_token}`,
      }
    : { ...config.headers };

  return axios({ ...config, headers, baseURL: BASE_URL });
};

export const saveAuthData = (loginResponse: LoginResponse) => {
  localStorage.setItem(token_key, JSON.stringify(loginResponse));
};

export const getAuthData = () => {
  const str = localStorage.getItem(token_key) ?? '{}';
  return JSON.parse(str) as LoginResponse;
};

export const removeAuthData = () => {
  localStorage.removeItem(token_key);
};

axios.interceptors.request.use(
  function (config) {
    return config;
  },
  function (error) {
    return Promise.reject(error);
  }
);

axios.interceptors.response.use(
  function (response) {
    return response;
  },
  function (error) {
    if (error?.response?.status === 401 || error?.response?.status === 403) {
      history.push('/admin/auth/login');
    }
    return Promise.reject(error);
  }
);

export const getTokenData = (): TokenData | undefined => {
  const token = getAuthData().access_token;
  try {
    return jwtDecode(token) as TokenData;
  } catch (error) {
    return undefined;
  }
};

export const isAuthenticated = (): boolean => {
  const tokenData = getTokenData();
  return tokenData !== undefined && tokenData?.exp * 1000 > Date.now();
};

export const hasAnyRoles = (roles: Role[]): boolean => {
  const tokenData = getTokenData();

  if (tokenData !== undefined) {
    return roles.some((role) => tokenData.authorities.includes(role));
  }

  return false;
};
