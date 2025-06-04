package com.aitor.chores.communication.users_groups

import com.aitor.chores.communication.TableReferenceNames
import com.aitor.chores.model.users_groups.UserGroupObject
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import kotlin.collections.HashMap

class UsersGroupQueries (db: FirebaseFirestore) {

    private val usersgroupsTable : CollectionReference = db.collection(TableReferenceNames.USER_GROUP)

    suspend fun getGroupById (id: String) : Any{
        val snap = usersgroupsTable.document(id).get().await()

        return if (snap.exists() && snap.data != null) {
            userGroupFromSnapshot(snap)
        } else {
            Any()
        }
    }

    private fun userGroupFromSnapshot (snap : DocumentSnapshot)
    : UserGroupObject
    {
        val name = snap.get(UsersGroupReferenceNames.GROUPNAME).toString()
        val members = snap.get(UsersGroupReferenceNames.MEMBERS) as List<HashMap<String, String>>
        val choresCompletedResponse = snap.get(UsersGroupReferenceNames.CHORESCOMPLETED) as List<HashMap<String, Any>>

        return UserGroupObject(snap.id, name, members,choresCompletedResponse)
    }
}