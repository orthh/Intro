import React, {useEffect, useState} from 'react'
import axios from 'axios';
import { useSelector, useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom'
import { Link } from 'react-router-dom';


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
  
  const dispatch = useDispatch();
  const navigate = useNavigate()
  const [products, setProducts] = useState<Product[]>([]);
  const [rentDays, setRentDays] = useState(new Map());
  const loginId = useSelector((state: State) => state.id);
  const token = useSelector((state: State) => state.token);
  const [search, setSearch] = useState("");
  const [searchData, setSearchData] = useState<Product[]>([]);


  // 모든 물품 불러오기
  const fetchAllProducts = async () => {
      try {
          const response = await axios.get(`${process.env.REACT_APP_API_URL}/product/all`);
          setProducts(response.data);

          const initialRentDays = new Map();
          response.data.forEach((product: Product) => initialRentDays.set(product.productId, 1));
          setRentDays(initialRentDays);
      } catch (error) {
          console.error('fetch failed', error);
      }
  };

  // 상품 검색 메서드
  const fetchSearch = async (param: string) => {
    try {
          const response = await axios.get(`${process.env.REACT_APP_API_URL}/product/search/${param}`);
          setSearchData(response.data);
          
      } catch (error) {
          console.error('fetch failed', error);
      }
  }
  

  useEffect(() => {
    fetchAllProducts();
  },[])

  useEffect(() => {
    if(search !== ""){
      fetchSearch(search);
    }
  },[search])

  return (
    <div className="py-16">
      <div className="flex m-8 justify-center">
        <p>상품 검색</p>
        <input
          type="text"
          className="border ml-3 w-[300px] pl-2"
          placeholder="상품명을 입력해주세요"
          value={search}
          onChange={(e) => {
            setSearch(e.target.value);
          }}
        ></input>
      </div>
        <table className="table-auto bg-white rounded-lg shadow-lg overflow-hidden mx-auto max-w-sm lg:max-w-7xl ">
            <thead>
            <tr>
                <th className="border-x px-14 py-2">상품명</th>
                <th className="border-x px-14 py-2">1일 대여가격</th>
                <th className="border-x px-14 py-2">주인</th>
                <th className="border-x px-14 py-2">대여일수</th>
                <th className="border-x px-14 py-2">상태</th>
                <th className="border-x px-14 py-2">결제하기</th>
            </tr>
            </thead>
        <tbody>
            {search === "" && products && products.length > 0 ?  products.map((product) =>
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
                      <Link to={`/pay`} state={{ product, rentDays }} >
                        <button className={`px-4 py-2 rounded ${product.status === 'available' ? 'bg-green-500' : 'bg-gray-500'}`} 
                                disabled={product.status !== 'available'}
                                // onClick={() => handleRent(product)}>
                                // onClick={() => handleRent(product)}>
                                >
                                  {product.status === 'available' ? '결제 하기' : '대여 불가'}
                        </button>
                      </Link>
                    </td>
                </tr>
            ) : searchData.map((product) => <tr>
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
                    <td className="border px-14 py-2">{product.status === "available" ? <span>결제</span>: <span>대여중</span>}</td>
                    <td className="border px-14 py-2">
                      <Link to={`/pay`} state={{ product, rentDays }} >
                        <button className={`px-4 py-2 rounded ${product.status === 'available' ? 'bg-green-500' : 'bg-gray-500'}`} 
                                disabled={product.status !== 'available'}
                                // onClick={() => handleRent(product)}>
                                // onClick={() => handleRent(product)}>
                                >
                                  {product.status === 'available' ? '결제' : '대여 불가'}
                        </button>
                      </Link>
                    </td>
                </tr>)}
            
        </tbody>
        </table>
    </div>
  )
}

export default Main