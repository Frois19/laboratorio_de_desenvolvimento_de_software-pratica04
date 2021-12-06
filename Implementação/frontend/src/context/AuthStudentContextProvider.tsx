import React, { createContext, ReactNode, useState } from "react";
import { useNavigate } from "react-router";


type AuthStudentContextType = {
    usernameAuth: string;
    handleJoin: (login: string, password: string) => void;
}


type AuthStudentContextProviderProps = {
    children: ReactNode;
}

export const AuthStudentContext = createContext({} as AuthStudentContextType);

export function AuthStudentContextProvider(props: AuthStudentContextProviderProps) {
    const [usernameAuth, setusernameAuth] = useState('');

    const navigate= useNavigate();


    async function handleJoin(login: string, password: string) {

        const obj = {
            login: login,
            password: password
        };
        const url = 'http://localhost:8080/student/login';
        await fetch(url, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*' },
            body: JSON.stringify(obj),
        }).then((resp) => {
            console.log(resp.status);
            if (resp.status == 200) {
                setusernameAuth(login);
                handleStudent();
            }
        });
    }

    function handleStudent() {
        navigate("/student/home");
    }


    return (
        <AuthStudentContext.Provider value={{ usernameAuth, handleJoin }}>
            {props.children}
        </AuthStudentContext.Provider>
    );
}