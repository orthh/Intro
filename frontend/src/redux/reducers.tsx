const storedDataString = localStorage.getItem('logindata');
let storedData = null;

if (storedDataString) {
  try {
    storedData = JSON.parse(storedDataString);
  } catch (error) {
    // console.error('Error parsing stored login data:', error);
  }
}
const initialState = {
  isLoggedIn: storedData ? storedData.isLoggedIn : false,
  id: storedData ? storedData.id : null,
  token: storedData ? storedData.token : null,
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
