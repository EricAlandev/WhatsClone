

export type ChatType = {
    id: number,
    idUserMessage: number,
    profile_picture?: string,
    message?: string,
    name?: string
    time?: string
    status?: string
    edited?: boolean
}

export type MessageType = {
   text: string,
   profilePicture?: string
}

export type selectedIds = {
    id: number,
    message:string,
    time: string,
    status: string
}
