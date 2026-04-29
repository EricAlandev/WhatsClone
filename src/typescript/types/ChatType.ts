

export type ChatType = {
    id: number,
    idUserMessage: number,
    profile_picture?: string,
    message?: string,
    name?: string
    time?: string
    status?: string
}

export type MessageType = {
   text: string,
   profilePicture?: string
}