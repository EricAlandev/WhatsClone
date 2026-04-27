'use client'

import React, { createContext, useContext, useState, useEffect, ReactNode } from 'react';
import { UserType } from '@/typescript/types/UserType';

interface AuthContextData {
    user: UserType | null;
    token: string | null;
    loading: boolean;
    login: (userData: UserType, token?: string) => void;
    logout: () => void;
}

const AuthContext = createContext<AuthContextData>({} as AuthContextData);

export function GlobalContext({ children }: { children: ReactNode }) {
    const [user, setUser] = useState<UserType | null>(null);
    const [token, setToken] = useState<string | null>(null);
    const [loading, setLoading] = useState<boolean>(true);

    useEffect(() => {
        const storedUser = localStorage.getItem('user');
        const storedToken = localStorage.getItem('token');
        
        if (storedUser) {
            try {
                setUser(JSON.parse(storedUser));
            } catch (error) {
                console.error("Failed to parse user from storage", error);
                localStorage.removeItem('user');
            }
        }

        if (storedToken) {
            try {
                setToken(JSON.parse(storedToken));
            } catch (error) {
                console.error("Failed to parse user from storage", error);
                localStorage.removeItem('token');
            }
        }
        
        setLoading(false);
    }, []);

    const login = (userData: UserType, token?: string) => {
        if(userData){
            setUser(userData);
            localStorage.setItem('user', JSON.stringify(userData));
        }   

        if(token){
            setToken(token);
            localStorage.setItem('token', JSON.stringify(token));
        }
    };

    const logout = () => {
        setUser(null);
        setToken(null);
        localStorage.removeItem('user');
        localStorage.removeItem('token');
    };

    return (
        <AuthContext.Provider 
            value={{ 
                user, 
                token,
                loading, 
                login, 
                logout 
            }}
        >
            {children}
        </AuthContext.Provider>
    );
}

export function useAuth() {
    const context = useContext(AuthContext);
    
    if (!context) {
        throw new Error("useAuth must be used within an AuthProvider");
    }
    
    return context;
}