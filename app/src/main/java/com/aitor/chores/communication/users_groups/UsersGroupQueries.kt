package com.aitor.chores.communication.users_groups

import com.aitor.chores.model.chores.ChoresRepo
import com.aitor.chores.model.users_groups.UserGroupChoresObject
import com.aitor.chores.model.users_groups.UserGroupMemberObject
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.tasks.await
import java.util.HashMap

class UsersGroupQueries (val collection: CollectionReference){

    suspend fun getGroupById (id: String){
        val snap = collection.document(id).get().await()

        if (snap.exists() && snap.data != null) {
            val id = snap.id.toString()
            val name = snap.get(UsersGroupReferenceNames.GROUPNAME).toString()
            val members = snap.get(UsersGroupReferenceNames.MEMBERS) as List<UserGroupMemberObject>
            val choresCompletedResponse = snap.get(UsersGroupReferenceNames.CHORESCOMPLETED)

            completedChores(choresCompletedResponse)
//            val group = UserGroupObject(
//                id,
//                name,
//                members,
//
//            )
//            println(group)
        }
    }

    private fun completedChores (chores : Any?)
//    : List<UserGroupChoresObject>
    {
        val untouchedchoreslist = chores as List<HashMap<String, Any>>
        val list = mutableListOf<UserGroupChoresObject>()

        for (item in untouchedchoreslist){
            if(ChoresRepo.myChore.isNotEmpty()){
                var chore = ChoresRepo.getById(item.get(UsersGroupReferenceNames.CHOREID).toString())
            } else {

            }


            println(item.get("doneBy"))
        }


    }
}