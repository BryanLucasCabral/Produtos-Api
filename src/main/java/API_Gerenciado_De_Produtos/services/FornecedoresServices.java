package API_Gerenciado_De_Produtos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import API_Gerenciado_De_Produtos.Repository.FornecedorRepository;
import API_Gerenciado_De_Produtos.dto.FornecedorDTO;
import API_Gerenciado_De_Produtos.model.Fornecedor;

@Service
public class FornecedoresServices {
    @Autowired
    private FornecedorRepository fornecedorRepository;

    public Fornecedor salvarFornecedor(Fornecedor fornecedor){
        return fornecedorRepository.save(fornecedor);
    }

    public Page<Fornecedor> listarFornecedores(Pageable paginacao){
        return fornecedorRepository.findAll(paginacao);
    }

    public FornecedorDTO buscarFornecedorPeloId(Long id){
        Optional<Fornecedor> fornecedorOpt = fornecedorRepository.findById(id);

        if (fornecedorOpt.isPresent()) {
            return fornecedorOpt.get().toDTO();
        }
        return null;
    }

    public void deletarFornecedores(Long id){
        fornecedorRepository.findById(id);
    }

    public Fornecedor atualizarFornecedor(Long id,Fornecedor dadosFornecedor){
        Optional<Fornecedor> fornecedorOpt = fornecedorRepository.findById(id);
        
        if (fornecedorOpt.isPresent()) {
            Fornecedor fornecedor = fornecedorOpt.get();

            fornecedor.setNome(dadosFornecedor.getNome());
            fornecedor.setCnpj(dadosFornecedor.getCnpj());

            return fornecedorRepository.save(fornecedor);
        }

        return null;
    }
}
