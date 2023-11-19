import React, {useEffect, useState} from 'react'
import axios from 'axios';
import { useSelector } from 'react-redux';


type Product = {
    name: string;
    nickname: string;
    price: string;
    productId: string;
    status: string;
    userId: string;
    rentDays?: number;
};

interface State {
  isLoggedIn: boolean;
  token: string | null;
  id: string | null;
}




const Main = () => {

  const [products, setProducts] = useState<Product[]>([]);
  const [rentDays, setRentDays] = useState(new Map());
  const loginId = useSelector((state: State) => state.id);
  const token = useSelector((state: State) => state.token);


  // 모든 물품 불러오기
  const fetchAllProducts = async () => {
      try {
          const response = await axios.get(`${process.env.REACT_APP_API_URL}/product/all`);
          console.log(response.data);
          setProducts(response.data);

          const initialRentDays = new Map();
          response.data.forEach((product: Product) => initialRentDays.set(product.productId, 1));
          setRentDays(initialRentDays);
      } catch (error) {
          console.error('fetch failed', error);
      }
  };

  // 대여 요청
  const handleRent = async (product: Product) => {
      if(loginId) {
        try {
          let days = rentDays.get(product.productId);
          const data = {
            productId: product.productId,
            orderUserId: loginId,
            rentDays: days
          }
          const response = await axios.post(`${process.env.REACT_APP_API_URL}/rental/save`, data, {
                    headers: {
                        'Authorization': `Bearer ${token}`
                    }
                })
          console.log(response.data);
        } catch (error) {
          console.error(error);
        }
    }else{
      alert('로그인이 필요합니다.');
    } 
      
  }

  useEffect(() => {
    fetchAllProducts();
  },[])

  return (
    <div className="py-16">
        <table className="table-auto bg-white rounded-lg shadow-lg overflow-hidden mx-auto max-w-sm lg:max-w-7xl ">
            <thead>
            <tr>
                <th className="border-x px-14 py-2">상품명</th>
                <th className="border-x px-14 py-2">1일 대여가격</th>
                <th className="border-x px-14 py-2">대여자</th>
                <th className="border-x px-14 py-2">대여일수</th>
                <th className="border-x px-14 py-2">상태</th>
                <th className="border-x px-14 py-2"></th>
            </tr>
            </thead>
        <tbody>
            {products && products.length > 0 &&  products.map((product) =>
                <tr>
                    <td className="border px-14 py-2">{product.name}</td>
                    <td className="border px-14 py-2">{product.price}원</td>
                    <td className="border px-14 py-2">{product.nickname}</td>
                    <td className="border px-14 py-2">
                      <select onChange={(e) => setRentDays(new Map(rentDays.set(product.productId, e.target.value)))}>
                          {[...Array(30).keys()].map((value) => 
                              <option value={value + 1}>{value + 1}일</option>
                          )}
                      </select>
                    </td>
                    <td className="border px-14 py-2">{product.status === "available" ? <span>대여가능</span>: <span>대여중</span>}</td>
                    <td className="border px-14 py-2">
                        <button className={`px-4 py-2 rounded ${product.status === 'available' ? 'bg-green-500' : 'bg-gray-500'}`} 
                                disabled={product.status !== 'available'}
                                onClick={() => handleRent(product)}>
                                  대여
                        </button>
                    </td>
                </tr>
            )}
        </tbody>
        </table>
    </div>
  )
}

export default Main