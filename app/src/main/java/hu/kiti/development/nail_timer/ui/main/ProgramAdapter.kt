package hu.kiti.development.nail_timer.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.kiti.development.nail_timer.databinding.ItemProgramBinding
import hu.kiti.development.nail_timer.models.Program

class ProgramAdapter(private var programs: List<Program>) :
    RecyclerView.Adapter<ProgramAdapter.ProgramViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgramViewHolder {
        val itemBinding =
            ItemProgramBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProgramViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ProgramViewHolder, position: Int) {
        val program: Program = programs[position]
        holder.bind(program)
    }

    override fun getItemCount(): Int {
        return programs.size
    }

    fun update(programs: List<Program>) {
        this.programs = programs
        notifyDataSetChanged()
    }

    class ProgramViewHolder(private val binding: ItemProgramBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(program: Program) {
            binding.programIdTextView.text = program.id.toString()
            binding.programNameTextView.text = program.name
        }
    }
}