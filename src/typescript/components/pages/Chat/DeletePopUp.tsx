

type DeletePopuP = {
    open: boolean;
    setIsModalDelete: any;
    deleteMessage: () => void;
}

export default function DeletePopuP({open, setIsModalDelete, deleteMessage} : DeletePopuP){

    if(open === false) return null;

    return(
        <>
            <div 
                onClick={() => {
                    setIsModalDelete(false);
                }}
                className="fixed inset-0 bg-[black] opacity-70"
            ></div>

            <div className="fixed top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 w-[85vw] h-[50vh] max-h-[200px] bg-[white] rounded-md">
                
                {/*Close buttom */}
                <header
                    className="flex items-center gap-2 pt-1.5 pl-3 pb-1.5 bg-[#161717] h-[60px]"
                >
                    <img
                        src={"/messages/close.png"}
                        onClick={() => {
                            setIsModalDelete(false);
                        }}
                        className="max-w-[28px] max-h-[28px]"
                    />
                </header>

                {/*Body popUp */}
                <div
                    className="relative h-full bg-[#2B2D2D]"
                >
                    <div
                        className="absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2"
                    >
                        <h3 className="text-[#F1F1F1] text-center">Excluir mensagens?</h3>

                        <div
                            className="flex justify-center gap-2 mt-4.5"
                        >
                            <button
                                onClick={() => {
                                    deleteMessage();
                                }}
                                className="w-[100px] p-2 text-[#F1F1F1] bg-[green] rounded-md"
                            >
                                Sim
                            </button>

                            <button
                                onClick={() => {
                                    setIsModalDelete(false);
                                }}
                                className="w-[100px] p-2 text-[#F1F1F1] bg-[red] rounded-md"
                            >
                                Não
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}