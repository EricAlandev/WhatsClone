'use client'

import { useEffect, useState } from "react";
import { callConfigurationsListService } from "../services/ServiceConfiguration";
import { ConfigsList } from "@/typescript/types/ConfigsListType";
import { useAuth } from "@/typescript/contexts/GlobalContext";

export default function useConfigurationUseCase(){
    console.log("inside of configurationUseCase");

    const [list, setList] = useState<ConfigsList[]>([]);

    const callConfigurationsList = async() => {
        try{
            const call : ConfigsList[] = await callConfigurationsListService();
            console.log("response of the call", call);

            setList(call);
        }

        catch(error){

        }
    }

    useEffect(() => {
        console.log("inside of useeffet");        
            callConfigurationsList();
    }, []);


    return {list};
}