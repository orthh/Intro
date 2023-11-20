import React, {useState, useEffect} from 'react'
import axios from 'axios';
import { useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom'



type Props = {}

interface State {
  isLoggedIn: boolean;
  token: string | null;
  id: string | null;
}

type Rental = {
  name: string;
  duedate: string;
  productId : string;
  startdate: string;
  rentalId: string;
}

const MyPage = (props: Props) => {

  const navigate = useNavigate()
  const loginId = useSelector((state: State) => state.id);
  const token = useSelector((state: State) => state.token);
  const [rentals, setRentals] = useState<Rental[]>([]);


  const fetchUserRentals = async () => {
    if(loginId){
      try {
        const response = await axios.get(`${process.env.REACT_APP_API_URL}/rental/get/${loginId}`, {
                    headers: {
                        'Authorization': `Bearer ${token}`
                    }
                });
        setRentals(response.data);
      } catch (error) {
        console.error('fetch failed', error);
      }
    }else{
      alert("로그인 해주세요"); 
      navigate("/");
    }
    
  }

  useEffect(() => {
    fetchUserRentals();
  }, [])

  const returnRental = async (rentalId: string) => {
    try {
        const response = await axios.patch(`${process.env.REACT_APP_API_URL}/rental/return/${rentalId}`, {}, {
          headers: {
              'Authorization': `Bearer ${token}`
          }
        });
        if(response.status === 200){
          fetchUserRentals();
          alert("반납 완료");
        }
      } catch (error) {
        console.error('fetch failed', error);
      }
  }

  return (
    <div className="py-16">
            <h1 className='text-center text-xl pb-10'>대여 목록</h1>
        <table className="table-auto bg-white rounded-lg shadow-lg overflow-hidden mx-auto max-w-sm lg:max-w-7xl ">
            <thead>
            <tr>
                <th className="border-x px-14 py-2">상품명</th>
                <th className="border-x px-14 py-2">대여시각</th>
                <th className="border-x px-14 py-2">만료시각</th>
                <th className="border-x px-14 py-2">반납하기</th>
            </tr>
            </thead>
        <tbody>
            {rentals && rentals.length > 0 ?  rentals.map((rental) =>
                <tr>
                    <td className="border px-14 py-2">{rental.name}</td>
                    <td className="border px-14 py-2">{rental.startdate}</td>
                    <td className="border px-14 py-2">{rental.duedate}</td>
                    <td className="border px-14 py-2">
                      
                        <button className={`px-4 py-2 rounded bg-green-500`} 
                                onClick={() => returnRental(rental.rentalId.toString())}>
                                  반납
                        </button>
                    </td>
                </tr>
            ) : <p className='text-center'>대여중인 상품이 없습니다.</p>}
        </tbody>
        </table>
    </div>
  )
}

export default MyPage