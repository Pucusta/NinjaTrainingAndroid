package hu.bme.aut.ninjatraining.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import hu.bme.aut.ninjatraining.data.ToplistItem
import hu.bme.aut.ninjatraining.databinding.DialogNewToplistItemBinding

class NewToplistItemDialogFragment : DialogFragment() {
    interface NewToplistItemDialogListener {
        fun onToplistItemCreated(newItem: ToplistItem)
    }

    private lateinit var listener: NewToplistItemDialogListener

    private lateinit var binding: DialogNewToplistItemBinding

    private var score: Int = 0

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
        return AlertDialog.Builder(requireContext())
            .setTitle("Score")
            .setView(binding.root)
            .setPositiveButton("Play") { _, i ->
                listener.onToplistItemCreated(getToplistItem())

                //TODO play again
            }
            .setNegativeButton("Menu") { _, i ->
                listener.onToplistItemCreated(getToplistItem())
                //TODO menu
            }
            .create()
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