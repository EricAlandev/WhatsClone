'use client'

import { UserType } from "@/typescript/types/UserType"
import { callAllChatsService, callAllMessagesService, searchFriendsService } from "../services/ServiceSearchFriends"
import { useState } from "react"
import { useAuth } from "@/typescript/contexts/GlobalContext";
import { ChatType, MessageType } from "@/typescript/types/ChatType";


export default function useChatUseCase(){

    const [originalChats, setOriginalChats] = useState<ChatType[] | []>([]);
    const [friendsFinded, setFriendsFinded] = useState<ChatType[] | []>([]);
    const [messages, setMessages] = useState<MessageType[] | []>([]);


    const {token} = useAuth();

    const SearchFriends = async(text: string) => {
        try{
            if(text && token){
                const resultSearch : ChatType[] = await searchFriendsService(text, token);
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
                setOriginalChats(resultSearch);
            }
        }

        catch(error){

        }
    }

    const callMessages = async(id: string, token: string) => {
        try{
            if(token){
                const resultSearch : MessageType[] = await callAllMessagesService(id, token);
                console.log("resultSearch", resultSearch);
                setMessages(resultSearch);
            }
        }

        catch(error){

        }
    }

    return {
        friendsFinded ,
        SearchFriends, 
        callAllChats, 
        callMessages,
        messages
    }
}