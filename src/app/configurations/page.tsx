'use client'

import AuthorizationComponent from "@/typescript/components/authorizations/AuthorizationComponent";
import HeaderConfig from "@/typescript/components/pages/configurations/HeaderConfigs";
import ListConfigurations from "@/typescript/components/pages/configurations/ListConfigurations";
import { useAuth } from "@/typescript/contexts/GlobalContext";
import useConfigurationUseCase from "@/typescript/servers/useCases/ConfigurationUseCase";

export default function ConfigurationsPage(){

    const {user} = useAuth();
    const {list, searchConfigurationsList} = useConfigurationUseCase();
    
    return(
        <div
            className="flex flex-col  min-h-screen pb-2 bg-[#161717]"
        >
                <AuthorizationComponent>
                    <HeaderConfig
                        name={user?.name}
                        searchProp={(text) => {
                            searchConfigurationsList(text)
                        }}
                    />
                    
                    <ListConfigurations
                        lists={list}
                    />  
                </AuthorizationComponent>
            
        </div>
    )
}