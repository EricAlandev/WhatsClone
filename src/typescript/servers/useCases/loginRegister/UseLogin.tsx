'use client'

import { UserType } from "@/typescript/types/UserType"
import { makeLogin } from "../../services/loginRegister/ServiceLogin"

export default function useLogin(){

    const login = async(data: UserType) => {
        if(data !== undefined && data){
            try{
                const verificationLogin = await makeLogin(data);
            }

            catch(error){

            }
        }
    }

    return{login}
}