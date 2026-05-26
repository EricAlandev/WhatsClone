'use client'

import { useEffect, useState } from "react";
import { callConfigurationsListService } from "../services/ServiceConfiguration";
import { ConfigsList } from "@/typescript/types/ConfigsListType";
import { useAuth } from "@/typescript/contexts/GlobalContext";

export default function useConfigurationUseCase(){

    //Save the original value that come from database.
    const [originalList, setOriginalList] = useState<ConfigsList[]>([]);

    //the value that the front-end use with the similar vlaues
    const [list, setList] = useState<ConfigsList[]>([]);

    const callConfigurationsList = async() => {
        try{
            const call : ConfigsList[] = await callConfigurationsListService();

            setOriginalList(call);
            setList(call);
        }

        catch(error){

        }
    }

    const searchConfigurationsList = async(prop?: string) => {
        try{

            if(!prop){
                setList(originalList)
            }
            
            else{
                const simularValues : ConfigsList[] = [];

                for(let i = 0; i < originalList.length; i++){
                    const ActualList = originalList[i];

                    if(ActualList.nameList.includes(prop)){
                        simularValues.push(ActualList);
                    }
                }

                //verify and set the props.
                setList(simularValues);
            }
        }

        catch(error){

        }
    }

    useEffect(() => {
            callConfigurationsList();
    }, []);


    return {list, searchConfigurationsList};
}