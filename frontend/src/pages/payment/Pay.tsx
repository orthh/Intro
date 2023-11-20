import React from 'react'
import axios from 'axios';
import { useLocation, useNavigate } from 'react-router-dom';
import { useSelector, useDispatch } from 'react-redux';
import { logout } from '../../redux/actions';



type Props = {}


interface State {
  isLoggedIn: boolean;
  token: string | null;
  id: string | null;
}


type Product = {
    name: string;
    nickname: string;
    price: string;
    productId: string;
    status: string;
    userId: string;
    rentDays?: number;
};


const Pay = (props: Props) => {

  const dispatch = useDispatch();
  const navigate = useNavigate()
  const location = useLocation();
  const product = location.state.product;
  const rentDays = location.state.rentDays.get(product.productId);
  const loginId = useSelector((state: State) => state.id);
  const token = useSelector((state: State) => state.token);

  // 대여 요청
  const handleRent = async (product: Product) => {
      if(loginId) {
        try {
          let days = rentDays;
          const data = {
            productId: product.productId,
            orderUserId: loginId,
            amount: (+product.price * days).toString(),
            rentDays: days
          }
          const response = await axios.post(`${process.env.REACT_APP_API_URL}/rental/save`, data, {
                    headers: {
                        'Authorization': `Bearer ${token}`
                    }
                })
          if (response.status === 200) { // 물품 대여 성공
            navigate("/mypage")
            alert("대여 성공");
          } 
        } catch (error) {
          console.error(error);
          dispatch(logout(0));
          localStorage.removeItem('logindata');
          alert("로그인해주세요");
        }
    }else{
      alert('로그인이 필요합니다.');
    } 
      
  }
  
  return (
    <div className="py-16">
        <div className="bg-white text-center rounded-lg shadow-lg overflow-hidden mx-auto max-w-sm lg:max-w-4xl py-10">
            <div className="flex-1">
            <h2 className="text-lg font-semibold text-gray-700">{product.name}</h2>
            <p className="text-gray-600">1일당 대여료: {product.price}원</p>
            <p className="text-gray-600">대여일수: {rentDays}일</p>
            <p className="text-gray-600">총 대여료: {product.price * rentDays}원</p>
          </div>
          <div className="flex-none mt-2">
            <button className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-400"
                onClick={() => handleRent(product)}>
              결제하기
            </button>
          </div>
          {/* <span>프로토타입으로 결제진행되지 않습니다.</span> */}
        </div>
    </div>
  )
}

export default Pay