const initialState = {
  isLoggedIn: false,
  id: null,
  token: null,
};

interface Res {
  token: string;
  id: string;
}

interface Action {
  type: string;
  payload?: any;
}

export default function loginReducer(state = initialState, action: Action) {
  switch (action.type) {
    case 'LOGIN_SUCCESS':
      return {
        ...state,
        isLoggedIn: true,
        id: action.payload.id,
        token: action.payload.token,
      };

    case 'LOGOUT':
      return {
        ...state,
        isLoggedIn: false,
        id: null,
        token: null,
      };
    default:
      return state;
  }
}
