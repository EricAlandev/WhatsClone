'use client'

export const searchPeopleToAddService = async(text: string, token: string) => {
    const loginFetch = await fetch(`${process.env.NEXT_PUBLIC_BASE_URL2}/api/users/AddFriends`, {
        method: 'POST',
        headers: {
            'Content-type' : 'application/json',
            'Authorization' : `Bearer ${token}`
        },
        body: JSON.stringify({search: text})
    })

    if(!loginFetch.ok){
        throw new Error("Server error" + loginFetch.status)
    }

    const response = await loginFetch.json();

    return response;
    
}

export const addFriendService = async(id: number, token: string) => {
    const loginFetch = await fetch(`${process.env.NEXT_PUBLIC_BASE_URL2}/api/users/AddFriends/${id}`, {
        method: 'POST',
        headers: {
            'Content-type' : 'application/json',
            'Authorization' : `Bearer ${token}`
        }
    })

    if(!loginFetch.ok){
        throw new Error("Server error" + loginFetch.status)
    }

    const response = await loginFetch.json();

    return response;
    
}