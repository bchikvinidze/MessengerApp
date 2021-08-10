package com.nchikvinidze.messengerapp.Home

import com.nchikvinidze.messengerapp.DependencyInjector
import com.nchikvinidze.messengerapp.data.MessageItem
import com.nchikvinidze.messengerapp.prefs

class HomePresenter(view: HomeList.View,
                    dependencyInjector: DependencyInjector
) : HomeList.Presenter {

    private val interactor = HomeInteractor(this)
    private var view: HomeList.View? = view
    private var list: MutableList<MessageItem> = mutableListOf()

    override fun onDestroy() {
        this.view = null
    }

    override fun scrolledDown() {
        view?.hideBottomNav()
    }

    override fun scrolledUp() {
        view?.showBottomNav()
    }

    override fun loadMessages() {
        // view?.showLoader()
        // interactor.loadMessageList()
    }

    override fun messagesLoaded(data: MutableList<MessageItem>) {
        list = data
        view?.hideLoader()
        view?.showMessages(data)
    }

    override fun addItem(item: MessageItem) {
        val nick = prefs.userName ?: ""
        val otherNick = if(item.from == nick) item.to else item.from
        val message = list.filter { it.from == otherNick || it.to == otherNick }
        if(message.size > 0) {
            list.remove(message[0])
        }
        list.add(0, item)
        list.sortByDescending{ it.timemillis }
        view?.showMessages(list)
    }

    override fun onViewCreated() {
        interactor.addListener()
    }
}
