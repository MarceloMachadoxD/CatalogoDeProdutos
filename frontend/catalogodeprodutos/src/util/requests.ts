import axios, { AxiosRequestConfig } from 'axios';
import qs from 'qs';

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
