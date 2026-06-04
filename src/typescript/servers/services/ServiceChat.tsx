import { selectedIds } from "@/typescript/types/ChatType";
import { UserType } from "@/typescript/types/UserType";

export const callAllMessagesService = async(id:string, token: string,) => {
    try{
        console.log("Inside of call service", id, token);
        const call = await fetch(`${process.env.NEXT_PUBLIC_BASE_URL2}/chat/messages/${id}`, {
            method: "GET",
            headers: {
                'Content-type' : 'application/json',
                'Authorization' : `Bearer ${token}`
            }
        })

        if(!call.ok){
            throw new Error("fail in the service fetch");
        }

        const response = await call.json();

        return response;
    }

    catch(error){

    }
}

export const callOptionsService = async(token: string,) => {
    try{
        const call = await fetch(`${process.env.NEXT_PUBLIC_BASE_URL2}/chat/options`, {
            method: "GET",
            headers: {
                'Content-type' : 'application/json',
                'Authorization' : `Bearer ${token}`
            }
        })

        if(!call.ok){
            throw new Error("fail in the service fetch");
        }

        const response = await call.json();

        return response;
    }

    catch(error){

    }
}

export const callUserDataService = async(id:string, token: string,) => {
    try{
        console.log("inside of callUserData");
        const call = await fetch(`${process.env.NEXT_PUBLIC_BASE_URL2}/chat/user/${id}`, {
            method: "GET",
            headers: {
                'Content-type' : 'application/json',
                'Authorization' : `Bearer ${token}`
            }
        })

        if(!call.ok){
            throw new Error("fail in the service fetch");
        }

        const response = await call.json();

        return response;
    }

    catch(error){

    }
}

export const sendMessageService = async(id:string, token: string,  message: any) => {
    try{
        console.log("Inside of call service", id, token, message);
        const call = await fetch(`${process.env.NEXT_PUBLIC_BASE_URL2}/chat/messages/${id}`, {
            method: "POST",
            headers: {
                'Content-type' : 'application/json',
                'Authorization' : `Bearer ${token}`
            },
            body: JSON.stringify({message : message?.text})
        })

        if(!call.ok){
            throw new Error("fail in the service fetch");
        }

        const response = await call.json();

        return response;
    }

    catch(error){

    }
}

export const deleteMessageService = async(ids: selectedIds[], token: string) => {
    try{
        const queryParams = ids.map(id => `id=${id?.id}`).join('&');

        const call = await fetch(`${process.env.NEXT_PUBLIC_BASE_URL2}/chat/messages/ids?${queryParams}`, {
            method: "DELETE",
            headers: {
                'Content-type' : 'application/json',
                'Authorization' : `Bearer ${token}`
            }
        })

        if(!call.ok){
            throw new Error("fail in the service fetch");
        }

        const response = await call.json();

        return response;
    }

    catch(error){

    }
}

export const alterMessageService = async(ids: selectedIds[], token: string, message: string) => {
    try{
        const queryParams = ids.map(id => `id=${id?.id}`).join('&');

        const call = await fetch(`${process.env.NEXT_PUBLIC_BASE_URL2}/chat/messages/ids?${queryParams}`, {
            method: "PUT",
            headers: {
                'Content-type' : 'application/json',
                'Authorization' : `Bearer ${token}`
            },
            body: JSON.stringify({
                message: message
            })
        })

        if(!call.ok){
            throw new Error("fail in the service fetch");
        }

        const response = await call.json();

        return response;
    }

    catch(error){

    }
}

export const fixMessageService = async(ids: selectedIds[], token: string, timeToFix: string) => {
    try{
        const queryParams = ids.map(id => `id=${id?.id}`).join('&');

        console.log("INSIDE OF FIXED MESSAGE SERVICE WITH THE VALUES", ids, token, timeToFix, queryParams);

        const call = await fetch(`${process.env.NEXT_PUBLIC_BASE_URL2}/chat/messages/fix/ids?${queryParams}`, {
            method: "PUT",
            headers: {
                'Content-type' : 'application/json',
                'Authorization' : `Bearer ${token}`
            },
            body: JSON.stringify({
                timeToFix
            })
        })

        if(!call.ok){
            throw new Error("fail in the service fetch");
        }

        const response = await call.json();

        return response;
    }

    catch(error){

    }
}

export const unfixMessageService = async(ids: selectedIds[], token: string) => {
    try{
        const queryParams = ids.map(id => `id=${id?.id}`).join('&');

        const call = await fetch(`${process.env.NEXT_PUBLIC_BASE_URL2}/chat/messages/fix/ids?${queryParams}`, {
            method: "DELETE",
            headers: {
                'Content-type' : 'application/json',
                'Authorization' : `Bearer ${token}`
            }
        })

        if(!call.ok){
            throw new Error("fail in the service fetch");
        }

        const response = await call.json();

        return response;
    }

    catch(error){

    }
}

export const verifyFixedMessageService = async(ids: Number[], token: string) => {
    try{
        const queryParams = ids.map(id => `id=${id}`).join('&');
        console.log("Inside of the serviceeee" , ids, token);

        
        const call = await fetch(`${process.env.NEXT_PUBLIC_BASE_URL2}/chat/messages/verify/fix/ids?${queryParams}`, {
            method: "PUT",
            headers: {
                'Content-type' : 'application/json',
                'Authorization' : `Bearer ${token}`
            }
        })

        if(!call.ok){
            throw new Error("fail in the service fetch");
        }

        const response : string = await call.json();

        return response;
    }

    catch(error){

    }
}

export const blockUserService = async(idChat: string, token: string) => {
    try{

        console.log("IdChat :", idChat, "Token :", token);

        const call = await fetch(`${process.env.NEXT_PUBLIC_BASE_URL2}/chat/user/block/${idChat}`, {
            method: "PUT",
            headers: {
                'Content-type' : 'application/json',
                'Authorization' : `Bearer ${token}`
            }
        })

        if(!call.ok){
            throw new Error("fail in the service fetch");
        }

        const response : string = await call.json();

        return response;
    }

    catch(error){

    }
}

export const unBlockUserService = async(idChat: string, token: string) => {
    try{

        const call = await fetch(`${process.env.NEXT_PUBLIC_BASE_URL2}/chat/user/unblock/${idChat}`, {
            method: "PUT",
            headers: {
                'Content-type' : 'application/json',
                'Authorization' : `Bearer ${token}`
            }
        })

        if(!call.ok){
            throw new Error("fail in the service fetch");
        }

        const response : string = await call.json();

        return response;
    }

    catch(error){

    }
}

export const clearMessagesService = async(idChat: string, token: string) => {
    try{
        console.log("before the fetch of the clean messages", idChat, token);
        const call = await fetch(`${process.env.NEXT_PUBLIC_BASE_URL2}/chat/messages/clear/${idChat}`, {
            method: "PUT",
            headers: {
                'Content-type' : 'application/json',
                'Authorization' : `Bearer ${token}`
            }
        })

        if(!call.ok){
            throw new Error("fail in the service fetch");
        }

        const response : string = await call.json();

        return response;
    }

    catch(error){

    }
}