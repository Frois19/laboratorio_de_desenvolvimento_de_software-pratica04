import React, { useState } from "react";
import { useNavigate } from "react-router";
import logoImg from "../../assets/subscribeUniversity.jpg";
import "../../styles/subscribe.scss"

export function SubscribeUniversity() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [name, setName] = useState('');
    const [cnpj, setCnpj] = useState('');

    const navigate = useNavigate();

    async function handleLogin() {
        const obj = {
            login: username,
            password: password,
            name: name,
            cnpj: cnpj
        };
        const url = 'http://localhost:8080/university';
        await fetch(url, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*' },
            body: JSON.stringify(obj),
        }).then((resp) => {
            console.log(resp.status);
            handleUniversity();
        });
    }

    function handleUniversity() {
        navigate("/university");
    }

    return (
        <main>
            <div className="main-content">
                <img src={logoImg} alt="logo" />
                <form>
                    <input type="text" placeholder="Username..." onChange={event => setUsername(event.target.value)} value={username} />
                    <input type="password" placeholder="Password..." onChange={event => setPassword(event.target.value)} value={password} />
                    <input type="text" placeholder="Name..." onChange={event => setName(event.target.value)} value={name} />
                    <input type="text" placeholder="CNPJ..." onChange={event => setCnpj(event.target.value)} value={cnpj} />
                    <button type="button" onClick={handleLogin}>Cadastrar</button>
                </form>
            </div>
        </main>
    )
}