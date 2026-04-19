'use client'

import { UserType } from "@/typescript/types/UserType"
import { makeLogin } from "../../services/loginRegister/ServiceLogin"

export default function useLogin(){

    const login = async(data: UserType) => {
        if(data){
            try{
                console.log("the data in front-end is", data);
                const verificationLogin = await makeLogin(data);
            }

            catch(error){

            }
        }
    }

    return{login}
}