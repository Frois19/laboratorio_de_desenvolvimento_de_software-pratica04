import React, { createContext, ReactNode, useState } from "react";
import { useNavigate } from "react-router";


type AuthContextType = {
    usernameAuth: string;
    handleJoin: (login: string, password: string) => void;
}


type AuthUniversityContextProviderProps = {
    children: ReactNode;
}

export const AuthUniversityContext = createContext({} as AuthContextType);

export function AuthUniversityContextProvider(props: AuthUniversityContextProviderProps) {
    const [usernameAuth, setusernameAuth] = useState('');

    const navigate= useNavigate();


    async function handleJoin(login: string, password: string) {

        const university = {
            login: login,
            password: password,
        };
        const url = 'http://localhost:8080/university/login';
        await fetch(url, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*' },
            body: JSON.stringify(university),
        }).then((resp) => {
            console.log(resp.status);
            if (resp.status == 200) {
                setusernameAuth(login);
                handleUniversity();
            }
        });
    }

    function handleUniversity() {
        navigate("/university/home");
    }


    return (
        <AuthUniversityContext.Provider value={{ usernameAuth, handleJoin }}>
            {props.children}
        </AuthUniversityContext.Provider>
    );
}