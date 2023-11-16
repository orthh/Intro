import React, {useEffect, useState} from 'react'
import axios from "axios";
import kakao from "../../assets/img/kakao.png";
import { useNavigate } from 'react-router-dom'
import { Link } from 'react-router-dom';

const Signup = () => {
  const navigate = useNavigate()
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPwd, setConfirmPwd] = useState('');
  const [nickname, setNickname] = useState('');
  const [emailError, setEmailError] = useState('');
  const [passwordError, setPasswordError] = useState('');
  const [nicknameError, setNicknameError] = useState('');
  const [error, setError] = useState('');

  useEffect(() => {
    if(password !== confirmPwd){
        setPasswordError("비밀번호가 일치하지 않습니다.");
    }else{
        setPasswordError("비밀번호가 일치합니다.");
    }
  },[password, confirmPwd])


  const handleSubmit = async (event: React.FormEvent) => {
        event.preventDefault();
        if(password === confirmPwd){
            try {
            const response = await axios.post('http://3.39.239.240:8081/v1/auth/signup', {
                email,
                password,
                nickname
            });
            if (response.status === 200) {
                // 로그인 성공 시 메인 페이지로 이동
                localStorage.setItem('token', response.data.token);
                navigate('/')
              } else {
                setError('로그인 실패');
              }
            } catch (error) {
                console.error('Login failed', error);
                setError('로그인 실패');
            }
        }
        
    };
  return (
      <div className="py-16">
            <div className="flex bg-white rounded-lg shadow-lg overflow-hidden mx-auto max-w-sm lg:max-w-4xl">
                <div className="w-full p-8 lg:w-full">
                    <p className="text-xl text-gray-600 text-center">회원가입</p>
                    <div className="mt-4">
                        <label className="block text-gray-700 text-sm font-bold mb-2">이메일</label>
                        <input className="bg-gray-200 text-gray-700 focus:outline-none border border-gray-300 rounded py-2 px-4 block w-full appearance-none" 
                               type="email" 
                               value={email}
                               onChange={(e) => setEmail(e.target.value)}/>
                    </div>
                    <div className="mt-4">
                        <div className="flex justify-between">
                            <label className="block text-gray-700 text-sm font-bold mb-2">비밀번호</label>
                        </div>
                        <input className="bg-gray-200 text-gray-700 focus:outline-none border border-gray-300 rounded py-2 px-4 block w-full appearance-none" 
                               type="password" 
                               value={password}
                               onChange={(e) => setPassword(e.target.value)}/>
                    </div>
                    <div className="mt-4">
                        <div className="flex justify-between">
                            <label className="block text-gray-700 text-sm font-bold mb-2">비밀번호 확인</label>
                        </div>
                        <input className="bg-gray-200 text-gray-700 focus:outline-none border border-gray-300 rounded py-2 px-4 block w-full appearance-none" 
                               type="password" 
                               value={confirmPwd}
                               onChange={(e) => setConfirmPwd(e.target.value)}/>
                        {confirmPwd!=="" && passwordError && <p className=" text-red-500">{passwordError}</p>}
                        
                    </div>
                    <div className="mt-4">
                        <div className="flex justify-between">
                            <label className="block text-gray-700 text-sm font-bold mb-2">닉네임</label>
                        </div>
                        <input className="bg-gray-200 text-gray-700 focus:outline-none border border-gray-300 rounded py-2 px-4 block w-full appearance-none" 
                               type="text" 
                               value={nickname}
                               onChange={(e) => setNickname(e.target.value)}/>
                    </div>
                    <div className="mt-8">
                        <button className="bg-gray-700 text-white font-bold py-2 px-4 w-full rounded hover:bg-gray-600"
                                onClick={handleSubmit}>가입하기</button>
                    </div>
                </div>
            </div>
        </div>
  )
}

export default Signup