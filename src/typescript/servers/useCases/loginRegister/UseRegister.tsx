'use client'

import { UserType } from "@/typescript/types/UserType"
import { makeRegister } from "@/typescript/servers/services/loginRegister/ServiceRegister";

export default function useRegister(){

    const register = async(data: UserType) => {
        if(data !== undefined && data){
            try{
                const registerUser = await makeRegister(data);

                return registerUser
            }

            catch(error){

            }
        }
    }

    return{register}
}