import { Link, useLocation, useNavigate } from "react-router-dom";
import { Box } from "../components/Box/Box";
import { Button } from "../components/Button/Button";
import { Input } from "../components/Input/Input";
import { Layout } from "../components/Layout/Layout";
import { Seperator } from "../components/Seperator/Seperator";
import { useState,  type FormEvent } from "react";
import classes from "./Login.module.scss";
import { useAuthentication } from "../contexts/AuthenticationContextProvider";

export function Login(){
    const [errorMessage, setErrorMessage] = useState("");
    const [isLoading, setIsLoading] = useState(false);
    const { login } = useAuthentication()!;
    const navigate = useNavigate();
    const location = useLocation();

    const doLogin = async(e:FormEvent<HTMLFormElement>)=>{
        e.preventDefault();
        setIsLoading(true);
        const email = e.currentTarget.email.value;
        const password = e.currentTarget.password.value;

        try{
            await login(email, password);
            const destination = location.state?.from || "/";
            navigate(destination);
        } catch (error) {
            if(error instanceof Error){
                setErrorMessage(error.message)
            }else{
                setErrorMessage("An unknown error occured.");
            }
        } finally {
            setIsLoading(false);
        }


    }

    return <Layout className={classes.root}>
        <Box>
            <h1>Sign in</h1>
            <p>Stay updated on your professional world.</p>
            <form onSubmit={doLogin}>
                <Input type="email" id="email" label="Email" onFocus={()=> setErrorMessage("")}/>
                <Input type="password" id="password" label="Password" onFocus={()=> setErrorMessage("")}/>
                {errorMessage && <h5 className={classes.error}>{errorMessage} </h5>}

                <Button type="submit" disabled={isLoading}>
                    {isLoading ? "..." : "Sign in"}
                </Button>
                {/* <Link to="/request-password-reset">Forget password?</Link> */}
            </form>
            <Seperator>Or</Seperator>
            <div className={classes.register}>
                New to LinkedIn? <Link to="/signup">Sign up</Link>
            </div>
        </Box>
    </Layout>
}