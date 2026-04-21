'use client'

import { UserType } from "@/typescript/types/UserType"
import { makeLogin } from "../services/loginRegister/ServiceLogin"
import { useAuth } from "@/typescript/contexts/GlobalContext";
import { useRouter } from "next/navigation";

export default function userUseCase(){


    const addFriends = async(data: UserType) => {
        if(data){
            try{

                const verificationLogin : UserType = await makeLogin(data);

            }

            catch(error){

            }
        }
    }

    return{addFriends}
}