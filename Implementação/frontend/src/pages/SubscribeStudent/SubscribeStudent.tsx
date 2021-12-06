import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router";
import logoImg from "../../assets/subscribeStudent.jpg";
import "../../styles/homeLogin.scss"
import { FormControl, Grid, InputLabel, MenuItem, Select } from "@material-ui/core";

export type University = {
    id: number
    login: string,
    password: string,
    name: string,
    cnpj: string
}


export function SubscribeStudent() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [name, setName] = useState('');
    const [cpf, setCpf] = useState('');
    const [rg, setRg] = useState('');
    const [adress, setAdress] = useState('');
    const [email, setEmail] = useState('');
    const [course, setCourse] = useState('');
    const [id_univeristy, setId_univeristy] = useState('');
    const [universites, setUniversites] = useState<University[]>([])

    const navigate = useNavigate();


    async function handleList() {
        await fetch('http://localhost:8080/university')
            .then(function (response) {
                return response.json();
            }).then(function (data) {
                setUniversites(data)
            });
    }

    useEffect(() => {
        handleList()
    }, [])

    async function handleLogin() {
        const obj = {
            login: username,
            password: password,
            name: name,
            cpf: cpf,
            rg: rg,
            adress: adress,
            email: email,
            course: course,
            id_univeristy: null
        };
        const url = 'http://localhost:8080/student';
        await fetch(url, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*' },
            body: JSON.stringify(obj),
        }).then((resp) => {
            console.log(resp.status);
            handleStudent();
        });
    }

    function handleStudent() {
        navigate("/student");
    }

    return (
        <main>
            <div className="main-content">
                <img src={logoImg} alt="logo" />
                <form>
                    <input type="text" placeholder="Username..." onChange={event => setUsername(event.target.value)} value={username} />
                    <input type="password" placeholder="Password..." onChange={event => setPassword(event.target.value)} value={password} />
                    <input type="text" placeholder="Name..." onChange={event => setName(event.target.value)} value={name} />
                    <input type="text" placeholder="CPF..." onChange={event => setCpf(event.target.value)} value={cpf} />
                    <input type="text" placeholder="RG..." onChange={event => setRg(event.target.value)} value={rg} />
                    <input type="text" placeholder="Adress..." onChange={event => setAdress(event.target.value)} value={adress} />
                    <input type="text" placeholder="Email..." onChange={event => setEmail(event.target.value)} value={email} />
                    <input type="text" placeholder="Course..." onChange={event => setCourse(event.target.value)} value={course} />
                    <Grid item xs={12}>
                        <FormControl style={{ minWidth: 200, marginTop: 20 }} fullWidth>
                            <InputLabel>University</InputLabel>
                            <Select defaultValue={""}>
                                {universites?.map(university => <MenuItem key={university.id} value={university.id}>
                                    {university.name}
                                </MenuItem>)}
                            </Select>
                        </FormControl>
                    </Grid>
                    <button type="button" onClick={handleLogin}>Cadastrar</button>
                </form>
            </div>
        </main>
    )
}