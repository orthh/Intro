interface Res {
  token: string;
  id: string;
}

export const loginSuccess = (res: Res) => ({
  type: 'LOGIN_SUCCESS',
  payload: res
});
export const logout = (res: any) => ({
  type: 'LOGOUT',
  payload: res
});
