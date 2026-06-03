

export type UserType = {
    id?: number,
    name?: string,
    email?: string,
    password?: string,
    birthday?: string,
    description?: string,
    city?: string,
    country?: string,
    ddd?: string,
    number?: string
}

export type UserWithClass = {
    id?: number,
    name?: string,
    email?: string,
    blocked?: boolean,
    userBlockedChat: boolean,
    password?: string,
    birthday?: string,
    description?: string,
    number: Number,
    nacionality: nacionality
}

export type Number = {
    ddd?: string,
    number?: string
}

export type nacionality = {
    city?: string,
    country?: string,
}