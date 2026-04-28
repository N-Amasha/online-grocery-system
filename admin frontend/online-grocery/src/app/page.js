import Image from "next/image";

export default function Home() {
  return (
    <div className="min-h-screen lg:flex">
        <aside className="w-full border-b border-[#7FC3FF]/70 bg-[#FFCCDE] px-5 py-6 lg:w-72 lg:border-b-0 lg:border-r lg:px-6 lg:py-8">
            <nav aria-label="Sidebar navigation" className="space-y-8">
            <section aria-labelledby="database-label" className="space-y-3">
                <div id="database-label" className="flex items-center gap-3">
                <span className="text-xs font-semibold uppercase tracking-[0.35em] text-[#7FC3FF]">database</span>
                <span className="h-px flex-1 bg-[#7FC3FF]/60"></span>
                </div>

                <div className="space-y-2">
                <button
                    type="button"
                    className="flex w-full items-center rounded-2xl border border-transparent px-4 py-3 text-left text-base font-medium text-[#E04582] transition-colors duration-200 hover:border-[#E04582]/25 hover:bg-[#BADFFF]/35 hover:text-[#E04582]"
                >
                    users
                </button>

                <button
                    type="button"
                    className="flex w-full items-center rounded-2xl border border-transparent px-4 py-3 text-left text-base font-medium text-[#E04582] transition-colors duration-200 hover:border-[#E04582]/25 hover:bg-[#BADFFF]/35 hover:text-[#E04582]"
                >
                    products
                </button>
                </div>
            </section>

            <section aria-labelledby="orders-label" className="space-y-3">
                <div id="orders-label" className="flex items-center gap-3">
                <span className="text-xs font-semibold uppercase tracking-[0.35em] text-[#7FC3FF]">orders</span>
                <span className="h-px flex-1 bg-[#7FC3FF]/60"></span>
                </div>

                <div className="space-y-2">
                <button
                    type="button"
                    className="flex w-full items-center rounded-2xl border border-transparent px-4 py-3 text-left text-base font-medium text-[#E04582] transition-colors duration-200 hover:border-[#E04582]/25 hover:bg-[#BADFFF]/35 hover:text-[#E04582]"
                >
                    users
                </button>

                <button
                    type="button"
                    className="flex w-full items-center rounded-2xl border border-transparent px-4 py-3 text-left text-base font-medium text-[#E04582] transition-colors duration-200 hover:border-[#E04582]/25 hover:bg-[#BADFFF]/35 hover:text-[#E04582]"
                >
                    stock
                </button>
                </div>
            </section>
            </nav>
        </aside>

        <main className="flex-1 px-4 py-5 sm:px-6 lg:px-8 lg:py-8"></main>  
    </div>
  );
}
