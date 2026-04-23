'use client'

import { UserType } from "@/typescript/types/UserType"
import { makeLogin } from "../services/loginRegister/ServiceLogin"
import { useAuth } from "@/typescript/contexts/GlobalContext";
import { useRouter } from "next/navigation";
import { addFriendService, searchPeopleToAddService } from "../services/ServiceUser";
import { useState } from "react";

export default function useUserCase(){
    const [values, setValues] = useState<UserType[] | []>([]);
    const {token} = useAuth();

    const searchPeopleToAdd = async(text: string) => {
        if(text && token){
            try{

                const listUsers : UserType[] = await searchPeopleToAddService(text, token);

                setValues(listUsers);

            }

            catch(error){

            }
        }
    }

    const addFriend = async(id: number, token: string) => {
        if(id && token){
            try{

                const add = await addFriendService(id, token);

            }

            catch(error){

            }
        }
    }

    return{values , searchPeopleToAdd, addFriend}
}