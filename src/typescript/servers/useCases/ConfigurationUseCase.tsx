'use client'

import { useEffect, useState } from "react";
import { callConfigurationsListService } from "../services/ServiceConfiguration";
import { ConfigsList } from "@/typescript/types/ConfigsListType";

export default function ConfigurationUseCase(){

    const [list, setList] = useState<ConfigsList[]>([]);

    useEffect(() => {
        console.log("inside of useeffet");        callConfigurationsList();
    }, []);

    const callConfigurationsList = async() => {
        try{
            const call : ConfigsList[] = await callConfigurationsListService();
            console.log("response of the call", call);

            setList(call);
        }

        catch(error){

        }
    }


    return {list};
}