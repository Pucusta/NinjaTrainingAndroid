package hu.bme.aut.ninjatraining.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import hu.bme.aut.ninjatraining.data.ToplistItem
import hu.bme.aut.ninjatraining.databinding.DialogNewToplistItemBinding

class NewToplistItemDialogFragment : DialogFragment() {
    interface NewToplistItemDialogListener {
        fun onToplistItemCreated(newItem: ToplistItem)
        fun onReturnToGame()
        fun onReturnToMenu()
        fun saveLastPlayerName(name: String)
        fun loadLastPlayerName(): String
    }

    private lateinit var listener: NewToplistItemDialogListener

    private lateinit var binding: DialogNewToplistItemBinding

    private var score: Int = 0

    private var returnToGame = true

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? NewToplistItemDialogListener
            ?: throw RuntimeException("Activity must implement the NewToplistItemDialogListener interface!")

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        score = arguments?.getInt("score") ?: throw IllegalStateException("No args provided")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogNewToplistItemBinding.inflate(LayoutInflater.from(context))
        binding.tvScore.text = score.toString()
        binding.etName.setText(listener.loadLastPlayerName())
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
            .setTitle("Score")
            .setView(binding.root)
            .setPositiveButton("Play") { _, i ->
                listener.onToplistItemCreated(getToplistItem())
                listener.saveLastPlayerName(binding.etName.text.toString())
                Toast.makeText(context, "Score saved!", Toast.LENGTH_SHORT).show()
                returnToGame = true
                dismiss()
            }
            .setNegativeButton("Menu") { _, i ->
                returnToGame = false
                dismiss()
            }
            .setNeutralButton("Save") { _, i ->
                listener.onToplistItemCreated(getToplistItem())
                Toast.makeText(context, "Score saved!", Toast.LENGTH_SHORT).show()
            }
            .setCancelable(false)
            .create()
        alertDialogBuilder.setCanceledOnTouchOutside(false)
        //alertDialogBuilder.setCancelable(false)
        return alertDialogBuilder
    }



    override fun onDestroy() {
        super.onDestroy()
        if (returnToGame)
            listener.onReturnToGame()
        else
            listener.onReturnToMenu()
    }

    private fun getToplistItem() = ToplistItem(
        name = binding.etName.text.toString(),
        score = Integer.parseInt(binding.tvScore.text.toString())
    )

    companion object {
        fun newInstance(score: Int): NewToplistItemDialogFragment = NewToplistItemDialogFragment().apply {
            arguments = Bundle().apply {
                putInt("score", score)
            }
        }
    }
}