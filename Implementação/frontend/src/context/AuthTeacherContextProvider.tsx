import React, { createContext, ReactNode, useState } from "react";
import { useNavigate } from "react-router";


type AuthContextType = {
    usernameAuth: string;
    handleJoin: (login: string, password: string) => void;
}


type AuthTeacherContextProviderProps = {
    children: ReactNode;
}

export const AuthTeacherContext = createContext({} as AuthContextType);

export function AuthTeacherContextProvider(props: AuthTeacherContextProviderProps) {
    const [usernameAuth, setusernameAuth] = useState('');

    const navigate= useNavigate();


    async function handleJoin(login: string, password: string) {

        const obj = {
            login: login,
            password: password
        };
        const url = 'http://localhost:8080/teacher/login';
        await fetch(url, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*' },
            body: JSON.stringify(obj),
        }).then((resp) => {
            console.log(resp.status);
            if (resp.status == 200) {
                setusernameAuth(login);
                handleTeacher();
            }
        });
    }

    function handleTeacher() {
        navigate("/teacher/home");
    }


    return (
        <AuthTeacherContext.Provider value={{ usernameAuth, handleJoin }}>
            {props.children}
        </AuthTeacherContext.Provider>
    );
}