package com.example.digikala.util

import android.app.Activity
import android.util.Log
import com.example.digikala.util.Constants.ZARINPAL_MERCHANT_ID
import com.zarinpal.ZarinPalBillingClient
import com.zarinpal.billing.purchase.Purchase
import com.zarinpal.client.BillingClientStateListener
import com.zarinpal.client.ClientState
import com.zarinpal.provider.core.future.FutureCompletionListener
import com.zarinpal.provider.core.future.TaskResult
import com.zarinpal.provider.model.response.Receipt

object ZarinpalPurchase {

    private val stateListener = object : BillingClientStateListener {

        override fun onClientServiceDisconnected() {

        }
        override fun onClientSetupFinished(state: ClientState) {

        }
    }

    fun purchase(
        amount: Long,
        description: String,
        activity: Activity,
        onPurchaseComplete: (String) -> Unit
    ) {

        val client = ZarinPalBillingClient.newBuilder(activity)
            .enableShowInvoice()
            .setListener(stateListener)
            .build()

        val purchase = Purchase.newBuilder()
            .asPaymentRequest(
                ZARINPAL_MERCHANT_ID,
                amount,
                "",
                description
            ).build()

        client.launchBillingFlow(
            purchase,
            object : FutureCompletionListener<Receipt> {
                override fun onComplete(task: TaskResult<Receipt>) {
                    if (task.isSuccess) {
                        val receipt = task.success
                        receipt?.transactionID?.let {
                            Log.e("transactionID", it)
                            onPurchaseComplete(it)
                        }
                    } else {
                        task.failure?.printStackTrace()
                    }
                }
            }
        )
    }

}