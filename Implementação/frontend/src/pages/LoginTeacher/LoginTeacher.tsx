import React, { useState } from "react";
import { useNavigate } from "react-router";
import ilustrationImg from "../../assets/teacher.jpg";
import { useAuthTeacher } from "../../hooks/useAuth";
import '../../styles/homeLogin.scss'

export function LoginTeacher() {
    const { handleJoin } = useAuthTeacher()

    const [login, setLogin] = useState('');
    const [password, setPassword] = useState('');

    const navigate = useNavigate();

    function handleSubscribe() {
        navigate("/subscribe/teacher");
    }

    return (
        <div id="page-auth">
            <aside>
                <img src={ilustrationImg} alt="" />
            </aside>
            <main>
                <div className="main-content">
                    <form>
                        <input type="text" placeholder="Username..." onChange={event => setLogin(event.target.value)} value={login} />
                        <input type="password" placeholder="Password..." onChange={event => setPassword(event.target.value)} value={password} />
                        <button type="button" onClick={() => handleJoin(login, password)}>Login</button>
                    </form>
                    <div className="separator">or create an account</div>
                    <button type="button" onClick={handleSubscribe}>Subscribe</button>
                </div>
            </main>
        </div>
    )
}