'use client'

import { UserType } from "@/typescript/types/UserType"
import { makeLogin } from "../../services/loginRegister/ServiceLogin"
import { useAuth } from "@/typescript/contexts/GlobalContext";
import { useRouter } from "next/navigation";

export default function useLogin(){
    
    const {user, token, login} = useAuth();
    const router = useRouter();

    const uselogin = async(data: UserType) => {
        if(data){
            try{
                console.log("the data in front-end is", data);
                const verificationLogin : UserType | any = await makeLogin(data);

                await login(
                    {
                        id: verificationLogin?.id, 
                        name: verificationLogin?.name,
                        email: verificationLogin?.email
                    },
                    verificationLogin?.token
                );
                
                router.push("/homepage");

            }

            catch(error){

            }
        }
    }

    return{uselogin}
}