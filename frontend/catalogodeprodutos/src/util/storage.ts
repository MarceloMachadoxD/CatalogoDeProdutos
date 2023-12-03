const token_key = 'authData';

type LoginResponse = {
    access_token: string;
    token_type: string;
    refresh_token: string;
    expires_in: number;
    scope: string;
    userFirstName: string;
    userId: number;
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
