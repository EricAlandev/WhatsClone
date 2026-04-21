'use client'

import AuthorizationComponent from "@/typescript/components/authorizations/AuthorizationComponent"
import Header from "@/typescript/components/headers/Header"
import HeaderConfig from "@/typescript/components/pages/configurations/HeaderConfigs";
import ListConfigurations from "@/typescript/components/pages/configurations/ListConfigurations";
import { useAuth } from "@/typescript/contexts/GlobalContext";
import ConfigurationUseCase from "@/typescript/servers/useCases/ConfigurationUseCase";
import userUseCase from "@/typescript/servers/useCases/UserUseCase"

export default function ConfigurationsPage(){

    const {user} = useAuth();
    const {list} = ConfigurationUseCase();
    
    return(
        <div
            className="flex flex-col  min-h-screen pb-2 bg-[#161717]"
        >
            <AuthorizationComponent>
                <HeaderConfig
                    name={user?.name}
                />
                
                <ListConfigurations
                    lists={list}
                />
            </AuthorizationComponent>
        </div>
    )
}