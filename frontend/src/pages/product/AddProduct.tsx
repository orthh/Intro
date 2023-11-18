import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom'
import { Link } from 'react-router-dom';
import { useSelector } from 'react-redux';

/*
* 물픔등록 페이지
*/

interface State {
  isLoggedIn: boolean;
  token: string | null;
  id: string | null;
}

const AddProduct = () => {
  const isLoggedIn = useSelector((state: State) => state.isLoggedIn);
  const userid = useSelector((state: State) => state.id);
  const token = useSelector((state: State) => state.token);


  const navigate = useNavigate()
  const [name, setName] = useState("");
  const [price, setPrice] = useState("");

   const handleSubmit = async (event: React.FormEvent) => {
        event.preventDefault();
        if(isLoggedIn){
            try {
                const response = await axios.post(`${process.env.REACT_APP_API_URL}/product/save`, {
                    name,
                    price,
                    userid: userid?.toString()
                }, {
                    headers: {
                        'Authorization': `Bearer ${token}`
                    }
                });
            if (response.status === 200) { // 물픔등록 성공
                navigate('/')
              } else {
                console.log(response.data);
              }
            } catch (error) {
                console.error('Login failed', error);
            }
        }
        
    };


return (
    <div className="py-16">
        <div className="flex bg-white rounded-lg shadow-lg overflow-hidden mx-auto max-w-sm lg:max-w-4xl">
                <div className="w-full p-8 lg:w-1/1">
                    <div className="mt-4">
                        <label className="block text-gray-700 text-sm font-bold mb-2">물품 이름</label>
                        <input className="bg-gray-200 text-gray-700 focus:outline-none border border-gray-300 rounded py-2 px-4 block w-full appearance-none" 
                                type="text" 
                                value={name}
                                onChange={(e) => setName(e.target.value)}/>
                    </div>
                    <div className="mt-4">
                        <div className="flex justify-between">
                            <label className="block text-gray-700 text-sm font-bold mb-2">가격</label>
                            {/* <button className="text-xs text-gray-500">Forget Password?</button> */}
                        </div>
                        <input className="bg-gray-200 text-gray-700 focus:outline-none border border-gray-300 rounded py-2 px-4 block w-full appearance-none" 
                                type="text" 
                                value={price}
                                onChange={(e) => setPrice(e.target.value)}/>
                    </div>
                    <div className="mt-8">
                        <button className="bg-gray-700 text-white font-bold py-2 px-4 w-full rounded hover:bg-gray-600"
                                onClick={handleSubmit}>등록</button>
                    </div>
                    
            </div>
        </div>
    </div>
);
}

export default AddProduct