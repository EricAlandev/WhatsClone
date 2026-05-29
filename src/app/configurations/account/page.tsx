'use client'

import AuthorizationComponent from "@/typescript/components/authorizations/AuthorizationComponent";
import HeaderAccount from "@/typescript/components/pages/configurations/account/HeaderAccount";
import { useAuth } from "@/typescript/contexts/GlobalContext";
import useAccount from "@/typescript/servers/useCases/useAccount";
import { Params } from "next/dist/server/request/params";
import { useParams } from "next/navigation";


export default function AccountPage(){

    const {token} = useAuth();
    const {changeAccount} = useAccount();

    return(
        <div
            className="min-h-screen bg-[#161717]"
        >
            <AuthorizationComponent>
               <HeaderAccount
                  send={(data) => {
                    if(token){
                        changeAccount(data, token);
                    }
                  }}
               />
            </AuthorizationComponent>
        </div>
    )
}