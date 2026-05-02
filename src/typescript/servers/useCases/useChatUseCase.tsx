'use client'

import { UserType } from "@/typescript/types/UserType"
import { callAllChatsService, searchFriendsService } from "../services/ServiceSearchFriends"
import { useCallback, useState } from "react"
import { useAuth } from "@/typescript/contexts/GlobalContext";
import { ChatType, MessageType } from "@/typescript/types/ChatType";
import { alterMessageService, callAllMessagesService, deleteMessageService, sendMessageService } from "../services/ServiceChat";


export default function useChatUseCase(){

    const [originalChats, setOriginalChats] = useState<ChatType[] | []>([]);
    const [friendsFinded, setFriendsFinded] = useState<ChatType[] | []>([]);
    const [messages, setMessages] = useState<ChatType[] | []>([]);

    const SearchFriends = async(text: string, token: string) => {
        try{
            if(text && token){
                const resultSearch : ChatType[] = await searchFriendsService(text, token);
                setFriendsFinded(resultSearch);
            }
        }

        catch(error){

        }
    }

    const callAllChats = async(token: string) => {
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

    const callMessages = useCallback(async(id: string, token: string) => {
        try{
            if(token){
                console.log("Inside of the call messages", token, id);
                const resultSearch : ChatType[] = await callAllMessagesService(id, token);
                console.log("resultSearch", resultSearch);
                setMessages(resultSearch);
            }
        }

        catch(error){

        }
    }, []);

    const sendMessage = async(id: string, token: string, message: string) => {
        try{
            if(token){
                const resultSearch : string = await sendMessageService(id, token, message);
                callMessages(id, token);
            }
        }

        catch(error){

        }
    };

    const deleteMessage = async(ids: number[], token: string, idChat: string) => {
        try{
            if(token){
                const resultSearch : string = await deleteMessageService(ids, token);
                callMessages(idChat, token);
            }
        }

        catch(error){

        }
    };

    const changeMessage = async(ids: number[], token: string, idChat: string, message: string) => {
        try{
            if(token){
                const resultSearch : string = await alterMessageService(ids, token, message);
                callMessages(idChat, token);
            }
        }

        catch(error){

        }
    };

    return {
        friendsFinded ,
        SearchFriends, 
        callAllChats, 
        callMessages,
        messages,
        sendMessage,
        deleteMessage,
        changeMessage
    }
}