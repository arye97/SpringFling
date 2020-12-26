import React, { useState } from "react";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import Navbar from 'react-bootstrap/Navbar'
import Nav from "react-bootstrap/Nav"
import "./Login.css";
import server from '../Api';

export default function Login() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    function validateForm() {
        return email.length > 0 && password.length > 0;
    }

    function handleSubmit(event) {
        event.preventDefault();
        login();
    }

    function login() {
        let loginData = { "email" : email,
                          "password" : password
        };
        server.login(loginData)
            .then(res => {
                //go to home page
                console.log(res.data);
                console.log("You have logged in successfully");
            }).catch(err => {
                console.log(err)
            })
    }

    return (



        <div >
            <div>
                <Navbar bg="light" expand="lg">
                    <Navbar.Brand href="#home">Ledger</Navbar.Brand>
                    <Navbar.Toggle aria-controls="basic-navbar-nav" />
                    <Navbar.Collapse id="basic-navbar-nav">
                        <Nav className="mr-auto"></Nav>
                        <Form inline className="headerButton">
                            <Button variant="success">Register</Button>
                            <Button variant="outline-danger">Logout</Button>
                        </Form>
                    </Navbar.Collapse>
                </Navbar>
            </div>
            <div className="Login">
                <Form onSubmit={handleSubmit}>
                    <Form.Group size="lg" controlId="email" >
                        <Form.Label>Email</Form.Label>
                        <Form.Control
                            autoFocus
                            placeholder="user@email.com"
                            type="email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                        />
                    </Form.Group>
                    <Form.Group size="lg" controlId="password">
                        <Form.Label>Password</Form.Label>
                        <Form.Control
                            placeholder="Your password"
                            type="password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                        />
                    </Form.Group>
                    <Button block size="lg" type="submit"  disabled={!validateForm()}>
                        Login
                    </Button>
                </Form>
            </div>
        </div>
    );
}