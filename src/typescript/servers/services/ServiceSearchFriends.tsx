
export const searchFriendsService = async(text: string, token: string,) => {
    try{
        const call = await fetch(`${process.env.NEXT_PUBLIC_BASE_URL2}/chat/search`, {
            method: "POST",
            headers: {
                'Content-type' : 'application/json',
                'Authorization' : `Bearer ${token}`
            },
            body: JSON.stringify({
                search: text
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


export const callAllChatsService = async(token: string,) => {
    try{
        const call = await fetch(`${process.env.NEXT_PUBLIC_BASE_URL2}/chat/pull`, {
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