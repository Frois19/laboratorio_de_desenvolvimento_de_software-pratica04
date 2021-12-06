import React from "react";
import { useNavigate } from "react-router";
import ilustrationImg from "../../assets/illustration.jpg";
import '../../styles/homeLogin.scss'


export function Home() {
    const navigate = useNavigate();

    function handleUniversity() {
        navigate("/university");
    }
    function handleTeacher() {
        navigate("/teacher");
    }
    function handleStudent() {
        navigate("/student");
    }
    function handleCompany() {
        navigate("/company");
    }

    return (
        <div id="page-auth">
            <aside>
                <img src={ilustrationImg} alt="" />
            </aside>
            <main>
                <div className="main-content">
                    <button type="button" onClick={handleUniversity}>University</button>
                    <div className="separator">🎓</div>
                    <button type="button" onClick={handleTeacher}>Teacher</button>
                    <div className="separator">👨‍🏫</div>
                    <button type="button" onClick={handleStudent}>Student</button>
                    <div className="separator">💼</div> 
                    <button type="button" onClick={handleCompany}>Company</button>
                </div>
            </main>
        </div>
    )
}