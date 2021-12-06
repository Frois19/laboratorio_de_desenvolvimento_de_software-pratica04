import { FormControl, Grid, InputLabel, MenuItem, Select } from "@material-ui/core";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router";
import logoImg from "../../assets/subscribeTeacher.jpg";
import "../../styles/subscribe.scss";

export type University = {
    id: number
    login: string,
    password: string,
    name: string,
    cnpj: string
}

export function SubscribeTeacher() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [name, setName] = useState('');
    const [cpf, setCpf] = useState('');
    const [department, setDepartment] = useState('');
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
        console.log(universites)
    }, [])

    async function handleLogin() {
        const obj = {
            login: username,
            password: password,
            name: name,
            cpf: cpf,
            department: department,
            id_univeristy: id_univeristy
        };
        const url = 'http://localhost:8080/teacher';
        await fetch(url, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*' },
            body: JSON.stringify(obj),
        }).then((resp) => {
            console.log(resp.status);
            handleTeacher();
        });
    }

    function handleTeacher() {
        navigate("/teacher");
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
                    <input type="text" placeholder="Department..." onChange={event => setDepartment(event.target.value)} value={department} />
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