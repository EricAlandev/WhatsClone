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

export const deleteMessageService = async(ids: number[], token: string) => {
    try{
        const queryParams = ids.map(id => `id=${id}`).join('&');

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