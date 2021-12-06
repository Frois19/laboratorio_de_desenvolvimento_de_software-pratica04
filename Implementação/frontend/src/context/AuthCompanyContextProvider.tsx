import React, { createContext, ReactNode, useState } from "react";
import { useNavigate } from "react-router";


type AuthCompanyContextType = {
    usernameAuth: string;
    handleJoin: (login: string, password: string) => void;
}


type AuthCompanyContextProviderProps = {
    children: ReactNode;
}

export const AuthCompanyContext = createContext({} as AuthCompanyContextType);

export function AuthCompanyContextProvider(props: AuthCompanyContextProviderProps) {
    const [usernameAuth, setusernameAuth] = useState('');

    const navigate= useNavigate();


    async function handleJoin(login: string, password: string) {

        const obj = {
            login: login,
            password: password
        };
        const url = 'http://localhost:8080/company/login';
        await fetch(url, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*' },
            body: JSON.stringify(obj),
        }).then((resp) => {
            console.log(resp.status);
            if (resp.status == 200) {
                setusernameAuth(login);
                handleCompany();
            }
        });
    }

    function handleCompany() {
        navigate("/company/home");
    }


    return (
        <AuthCompanyContext.Provider value={{ usernameAuth, handleJoin }}>
            {props.children}
        </AuthCompanyContext.Provider>
    );
}