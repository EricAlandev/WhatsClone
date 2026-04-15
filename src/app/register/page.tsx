'use client'

import RegisterForm from "@/typescript/components/pages/register/FormRegister";
import useRegister from "@/typescript/servers/useCases/loginRegister/UseRegister";


export default function RegisterPage(){

    const {register} = useRegister();

    return(
        <>
           <RegisterForm
                send={(data) => {
                    register(data);
                }}
           />
        </>
    )
}