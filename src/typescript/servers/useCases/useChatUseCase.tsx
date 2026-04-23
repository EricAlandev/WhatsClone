'use client'

import { UserType } from "@/typescript/types/UserType"
import { callAllChatsService, searchFriendsService } from "../services/ServiceSearchFriends"
import { useState } from "react"
import { useAuth } from "@/typescript/contexts/GlobalContext";
import { ChatType } from "@/typescript/types/ChatType";


export default function useChatUseCase(){

    const [friendsFinded, setFriendsFinded] = useState<ChatType[] | []>([]);

    const {token} = useAuth();

    const SearchFriends = async(text: string) => {
        try{
            if(text && token){
                const resultSearch : ChatType[] = await searchFriendsService(text, token);
                console.log("resultSearch", resultSearch);
                setFriendsFinded(resultSearch);
            }
        }

        catch(error){

        }
    }

    const callAllChats = async() => {
        try{
            if(token){
                const resultSearch : ChatType[] = await callAllChatsService(token);
                console.log("resultSearch", resultSearch);
                setFriendsFinded(resultSearch);
            }
        }

        catch(error){

        }
    }

    return {friendsFinded ,SearchFriends, callAllChats}
}